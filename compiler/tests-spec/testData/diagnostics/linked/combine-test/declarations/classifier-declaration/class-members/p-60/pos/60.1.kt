// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 60 -> sentence 60
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 60 -> sentence 60
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 60 -> sentence 60
 *                operator-overloading, destructuring-declarations -> paragraph 60 -> sentence 60
 * NUMBER: 1
 * DESCRIPTION: destructuring after invoke infers component types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
data class NamedPair(val first: Int, val second: String) {
    operator fun invoke(): NamedPair = this
}

fun case1() {
    val (a, b) = NamedPair(1, "a")()
    checkSubtype<Int>(a)
    checkSubtype<String>(b)
}
