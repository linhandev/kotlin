// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 66 -> sentence 66
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 66 -> sentence 66
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 66 -> sentence 66
 * NUMBER: 1
 * DESCRIPTION: captured invoke function reference returns Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun create(): () -> Int {
    var x = 0
    val o = object {
        operator fun invoke(): Int = ++x
    }
    return o::invoke
}

fun case1() {
    checkSubtype<Int>(create().let { it() + it() })
}
