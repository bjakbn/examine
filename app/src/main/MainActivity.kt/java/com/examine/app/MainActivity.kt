class MainActivity : AppCompatActivity() {

    private var selectedApp: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = findViewById<TextView>(R.id.tvSelectedApp)

        findViewById<Button>(R.id.btnSelectApp).setOnClickListener {
            startActivityForResult(
                Intent(this, SelectAppActivity::class.java),
                100
            )
        }

        findViewById<Button>(R.id.btnStart).setOnClickListener {
            val intent = Intent(this, MonitorService::class.java)
            intent.putExtra("pkg", selectedApp)
            startService(intent)
        }

        findViewById<Button>(R.id.btnStop).setOnClickListener {
            stopService(Intent(this, MonitorService::class.java))
        }

        findViewById<Button>(R.id.btnResult).setOnClickListener {
            startActivity(Intent(this, ResultActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            selectedApp = data?.getStringExtra("pkg")
            findViewById<TextView>(R.id.tvSelectedApp).text = selectedApp
        }
    }
}
