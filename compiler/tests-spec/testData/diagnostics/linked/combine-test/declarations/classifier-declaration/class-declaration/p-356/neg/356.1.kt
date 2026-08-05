// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 356 -> sentence 356
 * declarations, declaration-visibility -> paragraph 356 -> sentence 356
 * declarations, property-declaration -> paragraph 356 -> sentence 356
 * declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 356 -> sentence 356
 * NUMBER: 1
 * DESCRIPTION: 主构造 private val 在类外不可访问
 */

// TESTCASE NUMBER: 1
class User(private val id: Int)

fun test(): Int = User(1).<!INVISIBLE_MEMBER!>id<!>
