// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: object implementing interface override method returns expected value at runtime
 */

// TESTCASE NUMBER: 1
interface Service {
    fun run(): String
}

object AppService : Service {
    override fun run(): String = "OK"
}

fun box(): String {
    return if (AppService.run() == "OK") "OK" else "NOK"
}
