class FileEventWatcher(
    private val context: Context,
    private val pkg: String
) {

    private var running = false

    fun startWatching() {
        running = true

        Thread {
            while (running) {

                // ⚠️ 模拟行为检测（实际 Android 不能直接跨应用文件监听）
                val events = scanRecentFiles()

                events.forEach {
                    EventStore.add(
                        Event(
                            System.currentTimeMillis(),
                            it.first,
                            pkg,
                            it.second
                        )
                    )
                }

                Thread.sleep(2000)
            }
        }.start()
    }

    fun stopWatching() {
        running = false
    }

    private fun scanRecentFiles(): List<Pair<String, String>> {
        // 示例逻辑：模拟文件变化
        return emptyList()
    }
}
