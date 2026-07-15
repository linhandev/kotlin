// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, built-in-annotation-types -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: annotations, annotation-values -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: annotation classes are implicitly subtypes of kotlin.Annotation with allowed property types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.CLASS)
annotation class Ann1(val message: String)

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Repeatable
annotation class Ann2(val code: Int)

enum class E { A, B }

@Target(AnnotationTarget.CLASS)
annotation class Ann3(
    val s: String,
    val i: Int,
    val b: Boolean,
    val e: E,
    val nested: Ann1,
    val strings: Array<String>,
)

@Ann1("x") @Ann2(1) @Ann2(2)
class Case1

fun case_1() {
    checkSubtype<Annotation>(Ann1("x"))
    checkSubtype<Ann1>(Ann1("x"))
}

// TESTCASE NUMBER: 2
fun case_2(a: Ann1) {
    checkSubtype<Annotation>(a)
    checkSubtype<Ann1>(a)
    a.message checkType { check<String>() }
}
