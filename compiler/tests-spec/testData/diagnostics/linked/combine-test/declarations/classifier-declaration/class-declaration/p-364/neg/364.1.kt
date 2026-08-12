// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 364 -> sentence 364
 * declarations, declaration-visibility -> paragraph 364 -> sentence 364
 * declarations, property-declaration -> paragraph 364 -> sentence 364
 * declarations, function-declaration, extension-function-declaration -> paragraph 364 -> sentence 364
 * NUMBER: 1
 * DESCRIPTION: extension function cannot access class private property
 */

// TESTCASE NUMBER: 1
class C(private val secret: Int)
fun C.expose(): Int = <!INVISIBLE_MEMBER!>secret<!>
fun test() = C(1).expose()
