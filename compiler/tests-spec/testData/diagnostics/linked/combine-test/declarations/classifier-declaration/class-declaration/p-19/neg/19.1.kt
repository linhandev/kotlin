// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 19 -> sentence 19
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 19 -> sentence 19
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 19 -> sentence 19
 * NUMBER: 1
 * DESCRIPTION: class type parameter cannot be used as reified T::class
 */

// TESTCASE NUMBER: 1
class Box<T> { fun klass(): Any = <!TYPE_PARAMETER_AS_REIFIED!>T::class<!> }
