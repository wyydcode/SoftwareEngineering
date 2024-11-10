package com.example.softwareengineering

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "mistakes.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "mistakes"

        private const val COLUMN_ID = "id"
        private const val COLUMN_OPERATOR = "operator"
        private const val COLUMN_OPERAND1 = "operand1"
        private const val COLUMN_OPERAND2 = "operand2"
        private const val COLUMN_ANSWER = "answer"
        private const val COLUMN_ERROR_COUNT = "error_count" // 新增错误次数列
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = ("CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_OPERATOR TEXT, " +
                "$COLUMN_OPERAND1 INTEGER, " +
                "$COLUMN_OPERAND2 INTEGER, " +
                "$COLUMN_ANSWER INTEGER, " +
                "$COLUMN_ERROR_COUNT INTEGER DEFAULT 1)") // 默认错误次数为1
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // 删除错题记录
    fun deleteMistake(question: Question) {
        val db = writableDatabase
        val cursor = db.rawQuery(
            "SELECT $COLUMN_ID FROM $TABLE_NAME WHERE " +
                    "$COLUMN_OPERATOR = ? AND " +
                    "$COLUMN_OPERAND1 = ? AND " +
                    "$COLUMN_OPERAND2 = ?",
            arrayOf(question.operator, question.operand1.toString(), question.operand2.toString())
        )

        if (cursor.moveToFirst()) {
            val columnIndex = cursor.getColumnIndex(COLUMN_ID)
            val id = cursor.getInt(columnIndex)
            db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        }
        cursor.close()
        db.close()
    }


    suspend fun insertOrUpdateMistake(question: Question) {
        withContext(Dispatchers.IO) {
            val db = writableDatabase
            val cursor = db.rawQuery(
                "SELECT $COLUMN_ID, $COLUMN_ERROR_COUNT FROM $TABLE_NAME WHERE " +
                        "$COLUMN_OPERATOR = ? AND " +
                        "$COLUMN_OPERAND1 = ? AND " +
                        "$COLUMN_OPERAND2 = ?",
                arrayOf(question.operator, question.operand1.toString(), question.operand2.toString())
            )

            if (cursor.moveToFirst()) {
                val errorCountIndex = cursor.getColumnIndex(COLUMN_ERROR_COUNT)

                // 检查列索引是否有效
                if (errorCountIndex != -1) {
                    val currentCount = cursor.getInt(errorCountIndex)
                    val newCount = currentCount + 1

                    db.execSQL(
                        "UPDATE $TABLE_NAME SET $COLUMN_ERROR_COUNT = ? WHERE " +
                                "$COLUMN_OPERATOR = ? AND " +
                                "$COLUMN_OPERAND1 = ? AND " +
                                "$COLUMN_OPERAND2 = ? AND " +
                                "$COLUMN_ANSWER = ?",
                        arrayOf(newCount, question.operator, question.operand1, question.operand2, question.answer)
                    )
                }
            } else {
                // 如果没有找到，插入新的错题
                val values = ContentValues().apply {
                    put(COLUMN_OPERATOR, question.operator)
                    put(COLUMN_OPERAND1, question.operand1)
                    put(COLUMN_OPERAND2, question.operand2)
                    put(COLUMN_ANSWER, question.answer)
                    put(COLUMN_ERROR_COUNT, 1) // 错误次数初始化为1
                }
                db.insert(TABLE_NAME, null, values)
            }
            cursor.close()
            db.close()
        }
    }



    fun getAllMistakes(): List<Question> {
        val mistakes = mutableListOf<Question>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val operatorIndex = cursor.getColumnIndex(COLUMN_OPERATOR)
                val operand1Index = cursor.getColumnIndex(COLUMN_OPERAND1)
                val operand2Index = cursor.getColumnIndex(COLUMN_OPERAND2)
                val answerIndex = cursor.getColumnIndex(COLUMN_ANSWER)
                val errorCountIndex = cursor.getColumnIndex(COLUMN_ERROR_COUNT)

                // 检查列索引是否有效
                if (operatorIndex != -1 && operand1Index != -1 && operand2Index != -1 &&
                    answerIndex != -1 && errorCountIndex != -1) {

                    val operator = cursor.getString(operatorIndex)
                    val operand1 = cursor.getInt(operand1Index)
                    val operand2 = cursor.getInt(operand2Index)
                    val answer = cursor.getInt(answerIndex)
                    val errorCount = cursor.getInt(errorCountIndex)

                    mistakes.add(
                        Question(
                            operator,
                            operand1,
                            operand2,
                            answer,
                            isAnswered = false,
                            isCorrect = false,
                        )
                    )
                } else {
                    // 处理列索引无效的情况（例如记录日志或抛出异常）
                    // Log.e("Database Error", "One or more column indices are invalid")
                }

            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return mistakes
    }

}
