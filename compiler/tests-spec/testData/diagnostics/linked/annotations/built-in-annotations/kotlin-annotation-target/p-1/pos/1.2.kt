// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, built-in-annotations, kotlin-annotation-target -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Target meta-annotation with vararg multiple allowed targets compiles
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.CLASS, AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY)
annotation class MultiTarget17522(val value: Int)

@MultiTarget17522(1)
class MultiTargetClass17522

@MultiTarget17522(2)
fun multiTargetFun17522() {}

@MultiTarget17522(3)
val multiTargetProp17522: Int = 1
