package com.bitcodetech.taskmanager

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import com.bitcodetech.taskmanager.models.Task

class AddTaskActivity : AppCompatActivity() {

    private lateinit var edtTaskTitle: EditText
    private lateinit var edtTaskDescription: EditText
    private lateinit var edtTaskDueDate: EditText
    private lateinit var radioGroupTaskPriorities : RadioGroup
    private lateinit var btnSaveTask : Button

    private lateinit var radioTaskPriorityLow : RadioButton
    private lateinit var radioTaskPriorityNormal : RadioButton
    private lateinit var radioTaskPriorityHigh : RadioButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_activity)

        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        btnSaveTask.setOnClickListener(BtnSaveTaskClickListener())
    }

    private inner class BtnSaveTaskClickListener : View.OnClickListener {
        override fun onClick(view : View?) {

            val priority = if(radioTaskPriorityLow.isChecked) {
                Task.PRIORITY_LOW
            }
            else {
                if(radioTaskPriorityNormal.isChecked) {
                    Task.PRIORITY_NORMAL
                }
                else {
                    Task.PRIORITY_HIGH
                }
            }

            val task = Task(
                (System.currentTimeMillis().toInt()  % 1000),
                edtTaskTitle.text.toString(),
                edtTaskDescription.text.toString(),
                priority,
                edtTaskDueDate.text.toString()
            )

            val intentResult = Intent()
            intentResult.putExtra("task", task)

            setResult(1, intentResult)
            finish()

        }
    }

    private fun initViews() {
        edtTaskTitle = findViewById(R.id.edtTaskTitle)
        edtTaskDescription = findViewById(R.id.edtTaskDescription)
        edtTaskDueDate = findViewById(R.id.edtTaskDueDate)

        radioGroupTaskPriorities = findViewById(R.id.radioGroupTaskPriorities)
        btnSaveTask = findViewById(R.id.btnSaveTask)

        radioTaskPriorityLow = findViewById(R.id.radioTaskPriorityLow)
        radioTaskPriorityNormal = findViewById(R.id.radioTaskPriorityNormal)
        radioTaskPriorityHigh = findViewById(R.id.radioTaskPriorityHigh)
    }
}