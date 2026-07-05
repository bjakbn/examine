class SelectAppActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pm = packageManager
        val apps = pm.getInstalledApplications(0)

        val list = ListView(this)

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            apps.map { pm.getApplicationLabel(it).toString() + "\n" + it.packageName }
        )

        list.adapter = adapter

        list.setOnItemClickListener { _, _, position, _ ->
            val pkg = apps[position].packageName
            val intent = Intent()
            intent.putExtra("pkg", pkg)
            setResult(RESULT_OK, intent)
            finish()
        }

        setContentView(list)
    }
}
