package me.spirafy

import java.util.*

fun main(args : Array<String>) {
    val s = HashMap<String, String>()
    s.put("\$hi", "hey")
    System.out.println(replace("\$his", s))
}
private fun replace(line: String?, data: Map<String, String>): String? {
    var line = line
    for (t in data.keys) {
        line = line!!.replace(t, data[t].toString(), true)
    }
    return line;
}