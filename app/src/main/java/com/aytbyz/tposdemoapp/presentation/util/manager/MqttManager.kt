package com.aytbyz.tposdemoapp.presentation.util.manager

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.*

object MqttManager {
    @SuppressLint("StaticFieldLeak")
    private var mqttClient: MqttAndroidClient? = null

    var isConnected = false
        private set

    fun connect(context: Context, clientId: String = "TPOS1234", onResult: (Boolean) -> Unit) {
        if (mqttClient != null && isConnected) {
            onResult(true)
            return
        }

        val serverURI = "tcp://broker.hivemq.com:1883"
        mqttClient = MqttAndroidClient(context, serverURI, clientId)
        val options = MqttConnectOptions().apply {
            isAutomaticReconnect = true
            isCleanSession = true
        }

        mqttClient?.connect(options, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                isConnected = true
                Log.i("MQTT", "Connected")
                onResult(true)
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.e("MQTT", "Connection failed", exception)
                onResult(false)
            }
        })
    }

    fun publish(topic: String, payload: String, qos: Int = 1, onComplete: (Boolean) -> Unit) {
        if (!isConnected) return onComplete(false)
        val message = MqttMessage()
        message.payload = payload.toByteArray()
        message.qos = qos

        mqttClient?.publish(topic, message, null, object : IMqttActionListener {
            override fun onSuccess(asyncActionToken: IMqttToken?) {
                Log.i("MQTT", "Message published: $payload")
                onComplete(true)
            }

            override fun onFailure(asyncActionToken: IMqttToken?, exception: Throwable?) {
                Log.e("MQTT", "Publish failed", exception)
                onComplete(false)
            }
        })
    }

    fun disconnect() {
        mqttClient?.disconnect()
        isConnected = false
    }
}