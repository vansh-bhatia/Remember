package com.example.remember

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tinyDB:TinyDB= TinyDB(applicationContext)
        var arrayList1 = tinyDB.getListString("taskkey")

        if(arrayList1.isEmpty()) {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("How to use this app:\n\n1. To add a task, simply type your task in the text field next to the 'ADD TASK!' button and simply press the 'ADD TASK!' button to add your task. You will get a toast notification if saving is successful!\n\n" +
                    "2. Once your task is over, you will definitely want to delete your task from the list! That can be done by entering the task number in the box next to the 'delete' button. Once entered press the 'delete' button to remove the task. You will get a toast notification if deletion is successful\n\n" +
                    "3. All your active tasks can be viewed in the center of your screen.\n\n Personally, we find it the simple UI better if you use dark mode!\n To know more, visit the 'About Us section'!")
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                        //do things
                    })
            val alert: AlertDialog = builder.create()
            alert.show()
        }
        help_button.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("How to use this app:\n\n1. To add a task, simply type your task in the text field next to the 'ADD TASK!' button and simply press the 'ADD TASK!' button to add your task. You will get a toast notification if saving is successful!\n\n" +
                    "2. Once your task is over, you will definitely want to delete your task from the list! That can be done by entering the task number in the box next to the 'DELETE' button. Once entered press the 'DELETE' button to remove the task. You will get a toast notification if deletion is successful!\n\n" +
                    "3. All your active tasks can be viewed in the center of your screen.\n\n Personally, we find it the simple UI better if you use dark mode!\n To know more, visit the 'About' section!")
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                        //do things
                    })
            val alert: AlertDialog = builder.create()
            alert.show()
        }
        about_button.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this)
            builder.setMessage("Thank you for using Remember.\n\nRemember is made by Vansh Bhatia and is made using Kotlin and TinyDB! TinyDB is a lightweight database which is open source just like this app! Visit the Github Readme file to know more!\n" +
                    "\nAlso, on another note, this is my first app and was made after a ton of head banging and google searches!!")
                    .setCancelable(false)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
                        //do things
                    })
            val alert: AlertDialog = builder.create()
            alert.show()
        }



        //var arrayList = ArrayList<String>()
        //arrayList1.add("This is my first task")
        //arrayList1.add("This is my second task")
        userText.setText("")
        userText.setText(arrayList1.toString())
        var textString=""
        var factor=1
        for (text in arrayList1){
            val uptext="${factor}"+". "+ text
            textString=textString+uptext+'\n'
            factor += 1
        }
        userText.setText(textString)
        submitButton.setOnClickListener {
            if (userTask.text.toString() != "") {
                        if(arrayList1.size<15) {
                            arrayList1.add(userTask.text.toString())
                            tinyDB.putListString("taskkey", arrayList1)//update DB
                            //userText.setText(arrayList1.toString())
                            val toast = Toast.makeText(applicationContext,
                                    "Task Successfully Added! Make sure you complete it!",
                                    Toast.LENGTH_SHORT)

                            toast.show()
                            var textString = ""
                            var factor = 1
                            for (text in arrayList1) {
                                val uptext = "${factor}" + ". " + text
                                textString = textString + uptext + '\n'
                                factor += 1
                            }
                            userText.setText(textString)

                        }
                        else {
                            val toast = android.widget.Toast.makeText(applicationContext,
                                    "Maximum Task Limit Reached!",
                                    android.widget.Toast.LENGTH_SHORT)

                            toast.show()
                        }


            }
            userTask.setText("")
        }
        delete_button.setOnClickListener {
            if (delete_id.text.toString() == ""||delete_id.text.toString() == "."||delete_id.text.toString() == "+"||delete_id.text.toString() == "*"||delete_id.text.toString() == "#") {

            } else {
                if (delete_id.text.toString().toInt() >= 0 && delete_id.text.toString().toInt() <= 15) {
                    if (delete_id.text.toString().toInt() <= arrayList1.size && delete_id.text.toString().toInt() > 0) {

                        arrayList1.removeAt(delete_id.text.toString().toInt() - 1)
                        tinyDB.putListString("taskkey", arrayList1)
                        val toast = Toast.makeText(applicationContext,
                                " Successful!",
                                Toast.LENGTH_SHORT)

                        toast.show()
                        delete_id.setText("")
                        var textString=""
                        var factor=1
                        for (text in arrayList1){
                            val uptext="${factor}"+". "+ text
                            textString=textString+uptext+'\n'
                            factor += 1
                        }
                        userText.setText(textString)


                    } else {
                        val toast = Toast.makeText(applicationContext,
                                " Please enter the number corresponding to the task you want to remove!",
                                Toast.LENGTH_SHORT)
                        toast.show()
                        delete_id.setText("")

                    }
                } else {
                    val toast = Toast.makeText(applicationContext,
                            " Please enter the number corresponding to the task you want to remove!",
                            Toast.LENGTH_SHORT)
                    toast.show()
                    delete_id.setText("")

                }
            }
        }






    }

}