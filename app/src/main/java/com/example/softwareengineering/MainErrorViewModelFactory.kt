import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.softwareengineering.DatabaseHelper
import com.example.softwareengineering.ThirtyQuestionChallengeViewModel

class MathErrorViewModelFactory(private val dbHelper: DatabaseHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MathErrorViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MathErrorViewModel(dbHelper) as T
        }
        if (modelClass.isAssignableFrom(ThirtyQuestionChallengeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ThirtyQuestionChallengeViewModel(dbHelper) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
