package com.okre.appeightexercisememo

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.*

class MainActivity : AppCompatActivity() {

    val dataModelList = mutableListOf<DataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = Firebase.database
        val myRef = database.getReference("myMemo").child(Firebase.auth.currentUser!!.uid)

        val listView = findViewById<ListView>(R.id.mainListView)
        val listAdapter = ListViewAdapter(dataModelList)
        listView.adapter = listAdapter

        /*------------------------------------------------------------------------------------------
        * firebase 데이터 불러오기
        ------------------------------------------------------------------------------------------*/
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataModelList.clear()

                for(dataModel in dataSnapshot.children) {
                    Log.d("Data", dataModel.toString())
                    dataModelList.add(dataModel.getValue(DataModel::class.java)!!)
                }
                listAdapter.notifyDataSetChanged()
                Log.d("DataModel", dataModelList.toString())
            }
            override fun onCancelled(error: DatabaseError) {}
        })

        /*------------------------------------------------------------------------------------------
        * icon 버튼 눌렀을 경우
        ------------------------------------------------------------------------------------------*/
        val writeButton = findViewById<ImageView>(R.id.writeBtn)
        writeButton.setOnClickListener {
            val mBuilder = AlertDialog.Builder(this)
                .setView(R.layout.custom_dialog)
                .setTitle("운동 메모 다이얼로그")
                .show()

            var dateText = ""

            /*------------------------------------------------------------------------------------------
            * 다이얼로그에서 날짜 선택 버튼 눌렀을 경우
            ------------------------------------------------------------------------------------------*/
            val DateSelectBtn = mBuilder.findViewById<Button>(R.id.dateSelectBtn)
            DateSelectBtn?.setOnClickListener {
                // DatePickerDialog 에 표시할 달력
                val today = GregorianCalendar()
                val year : Int = today.get(Calendar.YEAR)
                val month : Int = today.get(Calendar.MONTH)
                val date : Int = today.get(Calendar.DATE)

                val datePicker = DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                        // 선택된 날짜..월은 0부터 시작하여 1을 더해야 함
                        Log.d("Main", "${year}, ${month +1}, ${dayOfMonth}")
                        DateSelectBtn.setText("${year}.${month +1}.${dayOfMonth}")

                        dateText = "${year}.${month +1}.${dayOfMonth}"
                    }
                }, year, month, date)
                datePicker.show()
            }

            /*------------------------------------------------------------------------------------------
            * 다이얼로그에서 저장하기 버튼 눌렀을 경우
            ------------------------------------------------------------------------------------------*/
            val saveBtn = mBuilder.findViewById<Button>(R.id.saveBtn)
            saveBtn?.setOnClickListener {
                val exerciseMemo = mBuilder.findViewById<EditText>(R.id.exerciseMemo)?.text.toString()
                val model = DataModel(dateText, exerciseMemo)

                myRef.push().setValue(model)

                mBuilder.dismiss()
            }
        }
    }
}

