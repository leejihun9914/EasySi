import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.ljh.easysi.R

class HListAdapter(context: Context, item1: ArrayList<String>, item2: ArrayList<String>) : BaseAdapter() {


    private val mContext = context
    private val mItem1 = item1
    private val mItem2 = item2

    private val mInflater = LayoutInflater.from(mContext)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var view = convertView
        lateinit var viewHolder : ViewHolder
        //뷰가 캐싱되지 않았을 경우 뷰를 만들고 뷰 홀더에 등록.
        if(view == null){
            viewHolder = ViewHolder()
            view = mInflater.inflate(R.layout.score_list,parent, false)
            viewHolder.textView = view!!.findViewById(R.id.textView)
            viewHolder.textView2 = view.findViewById(R.id.tv_c_score)
            viewHolder.checkBox = view.findViewById(R.id.checkBox)
            view.tag = viewHolder
            viewHolder.textView.text = mItem1[position]
            viewHolder.textView2.text = mItem2[position]
            return view
        }else{
            viewHolder = view.tag as ViewHolder
        }
        viewHolder.textView.text = mItem1[position]
        viewHolder.textView2.text = mItem2[position]
        return view
    }
    override fun getItem(position: Int) = mItem1[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = mItem1.size

    //내부클래스로 뷰홀더 생성
    inner class ViewHolder {
        lateinit var textView: TextView
        lateinit var textView2: TextView
        lateinit var checkBox: CheckBox
    }
}