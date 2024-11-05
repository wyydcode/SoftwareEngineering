import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.softwareengineering.GridAdapter
import com.example.softwareengineering.R
import com.example.softwareengineering.viewModel.SecondViewModel

class SecondFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: GridAdapter
    private val viewModel: SecondViewModel by viewModels() // 使用 ViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        // 观察 ViewModel 中的 itemList 数据
        viewModel.itemList.observe(viewLifecycleOwner, Observer { itemList ->
            // 数据变化时更新 RecyclerView
            adapter = GridAdapter(itemList)
            recyclerView.adapter = adapter
        })
    }
}
