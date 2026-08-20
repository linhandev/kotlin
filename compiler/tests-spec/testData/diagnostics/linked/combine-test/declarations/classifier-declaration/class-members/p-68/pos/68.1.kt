// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 68 -> sentence 68
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 68 -> sentence 68
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 68 -> sentence 68
 *                inheritance, overriding -> paragraph 68 -> sentence 68
 * NUMBER: 1
 * DESCRIPTION: overridden invoke infers String from subclass
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Base {
    open operator fun invoke(): String = "base"
}

class Derived : Base() {
    override operator fun invoke(): String = "derived"
}

fun case1() {
    checkSubtype<String>(Derived()())
}
