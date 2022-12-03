package com.example.clipboard

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface.OnClickListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var ctxt: EditText
    private lateinit var ptxt: EditText
    private lateinit var btncpy: Button
    private lateinit var btnpst: Button
    private lateinit var clipboardManager: ClipboardManager
    private lateinit var clipData: ClipData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ctxt = findViewById(R.id.txtCopy)
        ptxt = findViewById(R.id.txtShow)
        btncpy = findViewById(R.id.btnCopy)
        btnpst = findViewById(R.id.btnShow)
        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        btncpy.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                val txtToCopy = ctxt.text.toString()

                clipData = ClipData.newPlainText("text", txtToCopy)

                clipboardManager.setPrimaryClip(clipData)

                Toast.makeText(this@MainActivity, "text copied", Toast.LENGTH_LONG).show()
            }
        })

        btnpst.setOnClickListener(object :View.OnClickListener{
            override fun onClick(p0: View?) {
                val pData = clipboardManager.primaryClip

                val item = pData!!.getItemAt(0)

                val txtToPaste = item.text.toString()

                ptxt.setText(txtToPaste)

                Toast.makeText(this@MainActivity, "text to pasted", Toast.LENGTH_LONG).show()
            }
        })
    }
}