// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, data-class-declaration -> paragraph 14 -> sentence 14
 * PRIMARY LINKS: declarations, destructuring-declarations -> paragraph 14 -> sentence 14
 *                declarations, declaration-visibility -> paragraph 14 -> sentence 14
 * NUMBER: 1
 * DESCRIPTION: underscore can skip private primary constructor component inside the data class
 */

// TESTCASE NUMBER: 1
data class Secret(private val code: Int, val label: String) {
    fun exposed(): String {
        val (_, label) = this
        return label
    }
}

fun test(s: Secret): String = s.exposed()

fun box(): String {
    if (test(Secret(1, "ok")) != "ok") return "NOK"
    return "OK"
}
