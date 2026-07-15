// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: syntax-and-grammar, lexical-grammar, keywords-and-operators -> paragraph 75 -> sentence 75
 * NUMBER: 3
 * DESCRIPTION: BY token in class delegation interface implementation
 */
// TESTCASE NUMBER: 1

interface Worker75 {
    fun work(): String
}

class WorkerImpl75 : Worker75 {
    override fun work(): String = "kw-75-75-3"
}

class Delegator75(worker: Worker75) : Worker75 by worker

fun box(): String {
    val expected = "kw-75-75-3"
    val result = Delegator75(WorkerImpl75()).work()
    if (result != expected) return "NOK"
    return "OK"
}
