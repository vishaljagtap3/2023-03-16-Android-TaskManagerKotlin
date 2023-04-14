package com.bitcodetech.taskmanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.bitcodetech.taskmanager.models.Task

class MainActivity : AppCompatActivity() {

    private lateinit var btnAddTask: Button
    private lateinit var mainContainer: LinearLayout

    private val tasksList = ArrayList<Task>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initTasks()
        initViews()

        //bindTasksToViews()
        bindTasksToViewsNew()
        setupListeners()
    }

    private fun setupListeners() {
        btnAddTask.setOnClickListener(
            object : View.OnClickListener {
                override fun onClick(view : View?) {

                    val intent = Intent(this@MainActivity, AddTaskActivity::class.java)
                    startActivityForResult(intent, 1)

                }
            }
        )
    }

    private fun bindTasksToViewsNew() {

        for(task in tasksList) {

            val taskView = layoutInflater.inflate(R.layout.task_view, null)
            val txtTaskTitle = taskView.findViewById<TextView>(R.id.txtTaskTitle)
            val txtTaskDueDate = taskView.findViewById<TextView>(R.id.txtTaskDueDate)
            val txtTaskPriority = taskView.findViewById<TextView>(R.id.txtTaskPriority)

            txtTaskTitle.text = task.title
            txtTaskDueDate.text = task.dueDate
            txtTaskPriority.text = when(task.priority) {
                Task.PRIORITY_LOW -> "Low"
                Task.PRIORITY_NORMAL -> "Normal"
                Task.PRIORITY_HIGH -> "High"
                else -> {"NA"}
            }

            mainContainer.addView(taskView)

            taskView.setOnClickListener(TaskViewClickListener(task))
            /*taskView.setOnClickListener(
                object : View.OnClickListener {
                    override fun onClick(view : View?) {
                    }
                }
            )*/

        }
    }

    private inner class TaskViewClickListener(val task : Task) : View.OnClickListener {
        override fun onClick(view: View?) {

            val intent = Intent(this@MainActivity, TaskDetailsActivity::class.java)
            intent.putExtra("task", task)
            startActivity(intent)
        }
    }


    private fun bindTasksToViews() {
        for(task in tasksList) {

            val taskContainer = LinearLayout(this)
            taskContainer.orientation = LinearLayout.VERTICAL
            taskContainer.layoutParams =
                LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )

            val txtTaskTitle = TextView(this)
            txtTaskTitle.textSize = 20F
            txtTaskTitle.setPadding(16, 16, 16, 16)
            txtTaskTitle.layoutParams =
                ViewGroup.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            txtTaskTitle.text = task.title

            taskContainer.addView(txtTaskTitle)


            val txtTaskDueDate = TextView(this)
            txtTaskDueDate.textSize = 20F
            txtTaskDueDate.setPadding(16, 16, 16, 16)
            txtTaskDueDate.layoutParams =
                ViewGroup.LayoutParams(ViewGroup
                    .LayoutParams.MATCH_PARENT,
                    LayoutParams.WRAP_CONTENT
                )
            txtTaskDueDate.text = task.dueDate

            taskContainer.addView(txtTaskDueDate)

            mainContainer.addView(taskContainer)
        }
    }

    private fun initTasks() {
        tasksList.add(
            Task(
                101,
                "Pay Bills",
                "Electricity, Phone, Internet bills has to be paid",
                Task.PRIORITY_HIGH,
                "24-05-2023"
            )
        )

        tasksList.add(
            Task(
                101,
                "Do the assignments",
                "Complete the assignments on time...",
                Task.PRIORITY_LOW,
                "24-05-2025"
            )
        )
    }



    private fun initViews() {
        btnAddTask = findViewById(R.id.btnAddTask)
        mainContainer = findViewById(R.id.mainContainer)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(data == null) {
            return
        }

        val task = data!!.getSerializableExtra("task")  as Task
        tasksList.add(task)

        val taskView = layoutInflater.inflate(R.layout.task_view, null)
        val txtTaskTitle = taskView.findViewById<TextView>(R.id.txtTaskTitle)
        val txtTaskDueDate = taskView.findViewById<TextView>(R.id.txtTaskDueDate)
        val txtTaskPriority = taskView.findViewById<TextView>(R.id.txtTaskPriority)

        txtTaskTitle.text = task.title
        txtTaskDueDate.text = task.dueDate
        txtTaskPriority.text = when(task.priority) {
            Task.PRIORITY_LOW -> "Low"
            Task.PRIORITY_NORMAL -> "Normal"
            Task.PRIORITY_HIGH -> "High"
            else -> {"NA"}
        }

        mainContainer.addView(taskView)

        taskView.setOnClickListener(TaskViewClickListener(task))
    }


}