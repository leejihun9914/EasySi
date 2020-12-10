package com.ljh.easysi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.activity_my.*
import kotlinx.android.synthetic.main.drawer.*

class MyActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my)

        val database = Firebase.database
        val UserRef = database.getReference("users")

        UserRef.child(currentUser!!.uid).addValueEventListener(object :
            ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val user = dataSnapshot.getValue<User>()
                var starPasswd = ""
                for (i in 1..user!!.Password!!.length)
                    starPasswd += '*'
                tv_mp_name.text = user.UserName
                tv_mp_email.text = user.Email
                tv_mp_passwd.text = starPasswd

            }
            override fun onCancelled(error: DatabaseError) { }
        })

        btn_mp_change.setOnClickListener {
            if(btn_mp_change.text == "비밀번호 변경"){
                btn_mp_change.text = "입력 완료"
                et_mp_email.visibility = View.VISIBLE
            }
            else if(btn_mp_change.text == "입력 완료"){
                auth.sendPasswordResetEmail(et_mp_email.text.toString()).addOnCompleteListener { task ->
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext, "변경메일이 전송되었습니다!",Toast.LENGTH_SHORT).show()
                        et_mp_email.visibility = View.INVISIBLE
                        btn_mp_change.text = "비밀번호 변경"
                    }else Toast.makeText(this, task.exception.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
        btn_mp_back.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}