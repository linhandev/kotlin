// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 348 -> sentence 348
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 348 -> sentence 348
 * NUMBER: 1
 * DESCRIPTION: annotation with FUNCTION target cannot be applied to a class
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.FUNCTION)
annotation class FuncOnly

<!WRONG_ANNOTATION_TARGET!>@FuncOnly<!>
class MyClass(val x: Int)
