package com.bitcodetech.taskmanager

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.bitcodetech.taskmanager.models.Task

class TaskDetailsActivity : AppCompatActivity() {

    private lateinit var task : Task
    private lateinit var txtTaskTitle : TextView
    private lateinit var txtTaskDescription : TextView
    private lateinit var txtTaskDueDate : TextView
    private lateinit var txtTaskPriority : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.task_details_activity)

        task = intent.extras!!.getSerializable("task") as Task
        //task = intent.extras!!.getSerializable("task", Task::class.java)!!
        mt(task.title)

        initViews()
        bindTaskToViews()
    }

    private fun bindTaskToViews() {
        txtTaskTitle.text = task.title
        txtTaskDescription.text = task.description
        txtTaskPriority.text = when(task.priority) {
            Task.PRIORITY_LOW -> "Low"
            Task.PRIORITY_NORMAL -> "Normal"
            Task.PRIORITY_HIGH -> "High"
            else -> "NA"
        }
        txtTaskDueDate.text = task.dueDate
    }

    private fun initViews() {
        txtTaskTitle = findViewById(R.id.txtTaskTitle)
        txtTaskDescription = findViewById(R.id.txtTaskDescription)
        txtTaskPriority = findViewById(R.id.txtTaskPriority)
        txtTaskDueDate = findViewById(R.id.txtTaskDueDate)
    }

    private fun mt(text : String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

}