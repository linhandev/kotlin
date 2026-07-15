// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 55 -> sentence 55
 * NUMBER: 4
 * DESCRIPTION: SUPER_AT token in super@Worker from interface delegation override
 */
// TESTCASE NUMBER: 1

interface Task {
    fun run(): String
}

open class Worker : Task {
    override fun run() = "worker"
}

class Runner : Worker() {
    override fun run() = super@Runner.run() + "-done"
}

fun box(): String {
    return if (Runner().run() == "worker-done") "OK" else "NOK"
}
