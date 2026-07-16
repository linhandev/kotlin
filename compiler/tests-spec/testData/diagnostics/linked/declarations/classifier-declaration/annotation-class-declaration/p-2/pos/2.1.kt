// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, classifier-declaration, annotation-class-declaration -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: allowed annotation constructor parameter types
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class Nested(val message: String)

enum class E { A }

annotation class Allowed(
    val s: String,
    val i: Int,
    val b: Boolean,
    val e: E,
    val nested: Nested,
    val strings: Array<String>,
    val k: kotlin.reflect.KClass<*>,
)

fun case1(a: Allowed) {
    checkSubtype<Annotation>(a)
}
