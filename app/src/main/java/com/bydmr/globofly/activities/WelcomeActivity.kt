package com.bydmr.globofly.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bydmr.globofly.R
import com.bydmr.globofly.services.MessageService
import com.bydmr.globofly.services.ServiceBuilder
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_welcome)

		val messageService = ServiceBuilder.buildService(MessageService::class.java)
		val requestCall = messageService.getMessages("http://10.0.2.2:7000/messages") // farklı url'de de bu şekilde çalışılıyor

		requestCall.enqueue(object : Callback<String> {
			override fun onResponse(call: Call<String>, response: Response<String>) {
				if (response.isSuccessful) {
					val msg = response.body()
					msg.let {
						message.text = msg
					}
				}
			}

			override fun onFailure(call: Call<String>, t: Throwable) {

			}

		})
	}

	fun getStarted(view: View) {
		val intent = Intent(this, DestinationListActivity::class.java)
		startActivity(intent)
		finish()
	}
}
