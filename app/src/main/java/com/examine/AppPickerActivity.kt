class AppPickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pm = packageManager
        val apps = pm.getInstalledApplications(0)

        val listView = ListView(this)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            apps.map { it.packageName }
        )

        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, pos, _ ->
            val intent = Intent()
            intent.putExtra("pkg", apps[pos].packageName)
            setResult(RESULT_OK, intent)
            finish()
        }

        setContentView(listView)
    }
}
