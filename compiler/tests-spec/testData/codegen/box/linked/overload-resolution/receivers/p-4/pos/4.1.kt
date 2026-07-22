/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, receivers -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: member extension uses dispatch receiver and extension receiver together
 */

interface Target1104 {
    val suffix: String
}

class Host1104(val prefix: String) {
    fun Target1104.combine(): String = prefix + suffix
}

// TESTCASE NUMBER: 1
fun box(): String {
    val host = Host1104("OK")
    val target = object : Target1104 {
        override val suffix = ""
    }
    return with(host) {
        target.combine()
    }
}
