package com.ljh.easysi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_register.*


class RegisterActivity : AppCompatActivity() {
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val database = Firebase.database
        val UserRef = database.getReference("users")

        btn_r_registe.setOnClickListener {
            if(et_r_email.text.toString().isEmpty() || et_r_name.text.toString().isEmpty() ||
                et_r_passwd.text.toString().isEmpty() || et_r_repasswd.text.toString().isEmpty()
            ){
                Toast.makeText(this, "빈칸에 정보를 기입해주세요", Toast.LENGTH_SHORT).show()
            }
            if(et_r_passwd.text.toString() != et_r_repasswd.text.toString()){
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                et_r_repasswd.text = null
            }
            if(!checkBox.isChecked){
                Toast.makeText(this, "이용약관에 동의해주세요", Toast.LENGTH_SHORT).show()
            }
            else {
                auth.createUserWithEmailAndPassword(et_r_email.text.toString(), et_r_passwd.text.toString())
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            val cur_user = auth.currentUser
                            val user = User(et_r_email.text.toString(), et_r_name.text.toString(), et_r_passwd.text.toString())
                            UserRef.child(cur_user!!.uid).setValue(user)
                            Toast.makeText(applicationContext, "회원가입을 성공하셨습니다.", Toast.LENGTH_SHORT).show();

                            finish()
                        } else {
                            //비밀번호 글자 수 오류
                            if(et_r_passwd.length()<6) {
                                Toast.makeText(applicationContext, "비밀번호를 6글자 이상 입력해주세요", Toast.LENGTH_SHORT).show();
                                et_r_passwd.text = null
                                et_r_repasswd.text = null
                            }
                        }
                    }
            }
        }
    }
}