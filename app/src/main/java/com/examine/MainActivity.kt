class MainActivity : AppCompatActivity() {

    private var targetApp: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tvSelectedApp)

        findViewById<Button>(R.id.btnSelectApp).setOnClickListener {
            startActivityForResult(
                Intent(this, AppPickerActivity::class.java),
                100
            )
        }

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            val i = Intent(this, MonitorService::class.java)
            i.action = "START"
            i.putExtra("pkg", targetApp)
            startService(i)
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            startService(Intent(this, MonitorService::class.java).apply {
                action = "STOP"
            })
        }

        findViewById<Button>(R.id.btnResult).setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 100 && resultCode == RESULT_OK) {
            targetApp = data?.getStringExtra("pkg")
            findViewById<TextView>(R.id.tvSelectedApp).text = targetApp
        }
    }
}
