// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 60 -> sentence 60
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 60 -> sentence 60
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 60 -> sentence 60
 *                operator-overloading, destructuring-declarations -> paragraph 60 -> sentence 60
 * NUMBER: 1
 * DESCRIPTION: invoke result used in destructuring declaration
 */

// TESTCASE NUMBER: 1
data class NamedPair(val first: Int, val second: String) {
    operator fun invoke(): NamedPair = this
}

fun test(): Int {
    val (a, b) = NamedPair(1, "a")()
    return a + b.length
}

fun box(): String {
    if (test() != 2) return "NOK"
    return "OK"
}
