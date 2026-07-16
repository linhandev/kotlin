// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 4 -> sentence 4
 * NUMBER: 1
 * DESCRIPTION: annotation used on class function and parameter
 */

// TESTCASE NUMBER: 1
annotation class Marker(val value: Int)

@Marker(1)
class C {
    @Marker(2)
    fun f(@Marker(3) p: Int): Int = p
}
