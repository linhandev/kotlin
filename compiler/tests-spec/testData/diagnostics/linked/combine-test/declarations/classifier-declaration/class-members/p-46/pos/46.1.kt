// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-members -> paragraph 46 -> sentence 46
 * PRIMARY LINKS: operator-overloading, overview -> paragraph 46 -> sentence 46
 *                expressions, call-and-property-access-expressions, function-calls-and-property-access -> paragraph 46 -> sentence 46
 *                declarations, function-declaration -> paragraph 46 -> sentence 46
 * NUMBER: 1
 * DESCRIPTION: extension operator invoke infers String
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Box

operator fun Box.invoke(): String = "invoked"

fun case1() {
    checkSubtype<String>(Box()())
}
