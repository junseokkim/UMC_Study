package com.kimjunseok.chap9hw2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.kakao.sdk.user.UserApiClient
import com.kimjunseok.chap9hw2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    lateinit var viewBinding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        UserApiClient.instance.me { user, error ->
            viewBinding.userNickname.text ="닉네임: ${user?.kakaoAccount?.profile?.nickname}"
            viewBinding.userAddress.text = "이메일 주소: ${user?.kakaoAccount?.email}"
            viewBinding.userAge.text = "사용자 나이: ${user?.kakaoAccount?.ageRange}"

        }
    }
}