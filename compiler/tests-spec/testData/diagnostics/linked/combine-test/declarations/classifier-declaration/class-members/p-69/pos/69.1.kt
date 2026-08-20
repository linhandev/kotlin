// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 69 -> sentence 69
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 69 -> sentence 69
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 69 -> sentence 69
 * NUMBER: 1
 * DESCRIPTION: cast to invokable interface then invoke infers Int
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Invokable {
    operator fun invoke(): Int
}

class Impl : Invokable {
    override operator fun invoke(): Int = 42
}

fun case1(obj: Any) {
    checkSubtype<Int>((obj as Invokable)())
}
