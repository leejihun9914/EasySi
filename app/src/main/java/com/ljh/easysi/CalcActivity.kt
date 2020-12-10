package com.ljh.easysi

import HListAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_calc.*

class CalcActivity : AppCompatActivity() {
    private val mItem1 = ArrayList<String>()
    private val mItem2 = ArrayList<String>()
    private lateinit var mAdapter : HListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        mAdapter =  HListAdapter(this, mItem1, mItem2)
        listview.adapter = mAdapter
        //리스트뷰의 선택모드를 다중선택모드로 설정

        //추가버튼 리스너 등록
        btn_add.setOnClickListener {
            mItem1.add(et_c_cname.text.toString())  // 아이템추가.
            mItem2.add(et_c_score.text.toString())  // 아이템추가.
            mAdapter.notifyDataSetChanged()      // 어뎁터에 데이터 변경이 있음을 알림.
        }
        //삭제버튼 리스너 등록
        btn_del.setOnClickListener {
//            val checkedItem = listview.checkedItemPositions
//
//            //for문과 동일
//            (mAdapter.count downTo 0)
//                .filter { checkedItem.get(it) }
//                .forEach { mItem1.removeAt(it)
//                }
//
//            //선택초기화
//            listview.clearChoices()
//            //데이터가 변경됬음을 알림
//            mAdapter.notifyDataSetChanged()
            Toast.makeText(applicationContext, "미구현", Toast.LENGTH_SHORT).show()
        }
        btn_c_back.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}