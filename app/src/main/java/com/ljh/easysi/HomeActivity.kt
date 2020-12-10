package com.ljh.easysi

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.drawer.*

class HomeActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser

    lateinit var drawerLayout : DrawerLayout
    lateinit var drawerView : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val database = Firebase.database
        val UserRef = database.getReference("users")
        val ScheduleRef = database.getReference("users").child(currentUser!!.uid).child("schedule")

        val colors = ArrayList<String>() // Color 넣어줄 list
        colors.add("#ffab91")
        colors.add("#F48FB1")
        colors.add("#ce93d8")
        colors.add("#b39ddb")
        colors.add("#9fa8da")
        colors.add("#90caf9")
        colors.add("#81d4fa")
        colors.add("#80deea")
        colors.add("#80cbc4")
        colors.add("#c5e1a5")
        colors.add("#e6ee9c")
        colors.add("#fff59d")
        colors.add("#ffe082")
        colors.add("#ffcc80")
        colors.add("#bcaaa4")

        var colorIndex = 0

        val monId = arrayOf(R.id.mon1, R.id.mon2, R.id.mon3, R.id.mon4, R.id.mon5, R.id.mon6, R.id.mon7, R.id.mon8)
        val tueId = arrayOf(R.id.tue1, R.id.tue2, R.id.tue3, R.id.tue4, R.id.tue5, R.id.tue6, R.id.tue7, R.id.tue8)
        val wedId = arrayOf(R.id.wed1, R.id.wed2, R.id.wed3, R.id.wed4, R.id.wed5, R.id.wed6, R.id.wed7, R.id.wed8)
        val thuId = arrayOf(R.id.thu1, R.id.thu2, R.id.thu3, R.id.thu4, R.id.thu5, R.id.thu6, R.id.thu7, R.id.thu8)
        val friId = arrayOf(R.id.fri1, R.id.fri2, R.id.fri3, R.id.fri4, R.id.fri5, R.id.fri6, R.id.fri7, R.id.fri8)

        val mon = arrayOfNulls<TextView>(monId.size)
        val tue = arrayOfNulls<TextView>(tueId.size)
        val wed = arrayOfNulls<TextView>(wedId.size)
        val thu = arrayOfNulls<TextView>(thuId.size)
        val fri = arrayOfNulls<TextView>(friId.size)


        for(i in monId.indices){
            mon[i] = findViewById(monId[i])
            tue[i] = findViewById(tueId[i])
            wed[i] = findViewById(wedId[i])
            thu[i] = findViewById(thuId[i])
            fri[i] = findViewById(friId[i])
        }

        UserRef.child(currentUser.uid).addValueEventListener(object :
            ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue<User>()
                tv_h_name.text = user!!.UserName
                tv_m_name.text = user.UserName+" 님"
            }
            override fun onCancelled(error: DatabaseError) { }
        })


        ScheduleRef.addChildEventListener(object : ChildEventListener {
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            @SuppressLint("SetTextI18n")
            override fun onChildAdded(dataSnapshot: DataSnapshot, previousChildName: String?) {
                val schedule = dataSnapshot.getValue<Schedule>()
                when(schedule!!.Day){
                    "월요일" -> {
                        if(mon[schedule.StartTime]!!.text == "" ) {
                            mon[schedule.StartTime]!!.text = schedule.ClassName+'\n'+schedule.Professor
                            for (i in schedule.StartTime..schedule.EndTime) {
                                mon[i]!!.setBackgroundColor(schedule.Color)
                                mon[i]!!.setOnClickListener {
                                    InforSchedule(schedule)
                                }
                            }
                            for (i in (schedule.StartTime)+1..schedule.EndTime){
                                mon[i]!!.setTextColor(schedule.Color)
                                mon[i]!!.text = schedule.ClassName
                            }
                        }
                    }
                    "화요일" -> {
                        if(tue[schedule.StartTime]!!.text == "" ) {
                            tue[schedule.StartTime]!!.text = schedule.ClassName+'\n'+schedule.Professor
                            for (i in schedule.StartTime..schedule.EndTime) {
                                tue[i]!!.setBackgroundColor(schedule.Color)
                                tue[i]!!.setOnClickListener {
                                    InforSchedule(schedule)
                                }
                            }
                            for (i in (schedule.StartTime)+1..schedule.EndTime){
                                tue[i]!!.text = " "
                            }
                        }
                    }
                    "수요일" -> {
                        if(wed[schedule.StartTime]!!.text == "" ) {
                            wed[schedule.StartTime]!!.text = schedule.ClassName+'\n'+schedule.Professor
                            for (i in schedule.StartTime..schedule.EndTime) {
                                wed[i]!!.setBackgroundColor(schedule.Color)
                                wed[i]!!.setOnClickListener {
                                    InforSchedule(schedule)
                                }
                            }
                            for (i in (schedule.StartTime)+1..schedule.EndTime){
                                wed[i]!!.text = " "
                            }
                        }
                    }
                    "목요일" -> {
                        if(thu[schedule.StartTime]!!.text == "" ) {
                            thu[schedule.StartTime]!!.text = schedule.ClassName+'\n'+schedule.Professor
                            for (i in schedule.StartTime..schedule.EndTime) {
                                thu[i]!!.setBackgroundColor(schedule.Color)
                                thu[i]!!.setOnClickListener {
                                    InforSchedule(schedule)
                                }
                            }
                            for (i in (schedule.StartTime)+1..schedule.EndTime){
                                thu[i]!!.text = " "
                            }
                        }
                    }
                    "금요일" -> {
                        if(fri[schedule.StartTime]!!.text == "" ) {
                            fri[schedule.StartTime]!!.text = schedule.ClassName+'\n'+schedule.Professor
                            for (i in schedule.StartTime..schedule.EndTime) {
                                fri[i]!!.setBackgroundColor(schedule.Color)
                                fri[i]!!.setOnClickListener {
                                    InforSchedule(schedule)
                                }
                            }
                            for (i in (schedule.StartTime)+1..schedule.EndTime){
                                fri[i]!!.text = " "
                            }
                        }
                    }
                }
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                val schedule = snapshot.getValue<Schedule>()
                when(schedule!!.Day){
                    "월요일"->{
                        for (i in schedule.StartTime..schedule.EndTime){
                            mon[i]!!.setBackgroundResource(R.drawable.cell_shape)
                            mon[i]!!.text = ""
                            mon[i]!!.setOnClickListener {}
                        }
                    }
                    "화요일"->{
                        for (i in schedule.StartTime..schedule.EndTime){
                            tue[i]!!.setBackgroundResource(R.drawable.cell_shape)
                            tue[i]!!.text = ""
                            tue[i]!!.setOnClickListener {}
                        }
                    }
                    "수요일"->{
                        for (i in schedule.StartTime..schedule.EndTime){
                            wed[i]!!.setBackgroundResource(R.drawable.cell_shape)
                            wed[i]!!.text = ""
                            wed[i]!!.setOnClickListener {}
                        }
                    }
                    "목요일"->{
                        for (i in schedule.StartTime..schedule.EndTime){
                            thu[i]!!.setBackgroundResource(R.drawable.cell_shape)
                            thu[i]!!.text = ""
                            thu[i]!!.setOnClickListener {}
                        }
                    }
                    "금요일"->{
                        for (i in schedule.StartTime..schedule.EndTime){
                            fri[i]!!.setBackgroundResource(R.drawable.cell_shape)
                            fri[i]!!.text = ""
                            fri[i]!!.setOnClickListener {}
                        }
                    }
                }
            }

        })

        btn_edit.setOnClickListener {
            val linear = View.inflate(this, R.layout.add_schedule, null) as LinearLayout
            AlertDialog.Builder(this)
                .setTitle("강의시간 추가")
                .setCancelable(false)
                .setView(linear)
                .setPositiveButton("확인") { _, _ ->
                    val cname = linear.findViewById<EditText>(R.id.tv_s_cname).text.toString()
                    val professor = linear.findViewById<EditText>(R.id.tv_s_professor).text.toString()
                    val place = linear.findViewById<EditText>(R.id.tv_s_place).text.toString()
                    val day = linear.findViewById<Spinner>(R.id.sp_day).selectedItem.toString()
                    val stime = linear.findViewById<Spinner>(R.id.sp_time_start).selectedItem.toString().toInt()-1
                    val etime = linear.findViewById<Spinner>(R.id.sp_time_end).selectedItem.toString().toInt()-1
                    val strColor = colors.removeAt(colorIndex)
                    val color = Color.parseColor(strColor)
                    colorIndex += 1

                    if(cname.isEmpty() || professor.isEmpty() || place.isEmpty() || day.isEmpty()){
                        Toast.makeText(this, "빈칸을 제대로 기입해주세요",Toast.LENGTH_SHORT).show()
                    }
                    else {
                        val schedule = Schedule(cname, professor, place, day, stime, etime, color)
                        var check = true

                        when (day) {
                            "월요일" -> {
                                for (i in stime..etime) {
                                    if (mon[i]!!.text != "") {
                                        check = false
                                    }
                                }
                                if (check) {
                                    ScheduleRef.child(cname).setValue(schedule)
                                } else Toast.makeText(this, "이미 등록된 강의가 있습니다", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            "화요일" -> {
                                for (i in stime..etime) {
                                    if (tue[i]!!.text != "") {
                                        check = false
                                    }
                                }
                                if (check) {
                                    ScheduleRef.child(cname).setValue(schedule)
                                } else Toast.makeText(this, "이미 등록된 강의가 있습니다", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            "수요일" -> {
                                for (i in stime..etime) {
                                    if (wed[i]!!.text != "") {
                                        check = false
                                    }
                                }
                                if (check) {
                                    ScheduleRef.child(cname).setValue(schedule)
                                } else Toast.makeText(this, "이미 등록된 강의가 있습니다", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            "목요일" -> {
                                for (i in stime..etime) {
                                    if (thu[i]!!.text != "") {
                                        check = false
                                    }
                                }
                                if (check) {
                                    ScheduleRef.child(cname).setValue(schedule)
                                } else Toast.makeText(this, "이미 등록된 강의가 있습니다", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            "금요일" -> {
                                for (i in stime..etime) {
                                    if (fri[i]!!.text != "") {
                                        check = false
                                    }
                                }
                                if (check) {
                                    ScheduleRef.child(cname).setValue(schedule)
                                } else Toast.makeText(this, "이미 등록된 강의가 있습니다", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                    }
                }
                .setNegativeButton("취소", null)
                .show()
        }

        btn_delete.setOnClickListener {
            AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog)
                    .setTitle("일정 삭제")
                    .setMessage("정말로 삭제 하시겠습니까?")
                    .setPositiveButton("네") { _, _ ->
                        val choiceName = tv_h_cname.text.toString()
                        ScheduleRef.child(choiceName).removeValue()
                    }
                    .setNegativeButton("아니요", null)
                    .show()
        }

        drawerLayout = findViewById(R.id.DrawerLayout)
        drawerView = findViewById(R.id.drawerView)

        val myLayout = findViewById<LinearLayout>(R.id.myLayout)
        val calLayout = findViewById<LinearLayout>(R.id.calLayout)
        val helpLayout = findViewById<LinearLayout>(R.id.helpLayout)

        btn_menu.setOnClickListener {
            drawerLayout.openDrawer(drawerView)
        }

        myLayout.setOnClickListener {
            val intent = Intent(this, MyActivity::class.java)
            startActivity(intent)
        }
        calLayout.setOnClickListener {
            val intent = Intent(this, CalcActivity::class.java)
            startActivity(intent)
        }
        helpLayout.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }

        btn_m_logout.setOnClickListener {
            AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Light_Dialog)
                .setTitle("Logout")
                .setMessage("정말로 로그아웃 하시겠습니까?")
                .setPositiveButton("네") { _, _ ->
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    auth.signOut()
                    finish()
                }
                .setNegativeButton("아니요", null)
                .show()
        }
    }
    fun InforSchedule(schedule: Schedule){
        btn_delete.visibility = View.VISIBLE
        tv_h_ttitle.visibility = View.VISIBLE
        tv_h_tplace.visibility = View.VISIBLE

        tv_h_cname.text = schedule.ClassName
        tv_h_tname.text = schedule.Professor
        tv_h_place.text = schedule.Place
    }

    override fun onBackPressed() {
        //super.onBackPressed();
    }
}


