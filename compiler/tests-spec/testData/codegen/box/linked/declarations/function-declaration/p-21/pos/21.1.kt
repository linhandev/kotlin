// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, function-declaration -> paragraph 21 -> sentence 21
 * NUMBER: 1
 * DESCRIPTION: reified type parameters support runtime type checks and class literals
 */

// TESTCASE NUMBER: 1
inline fun <reified T> isInstance(value: Any?): Boolean = value is T

inline fun <reified T> classLiteralName(): String = T::class.simpleName ?: ""

fun box(): String {
    if (!isInstance<String>("ok")) return "NOK is String"
    if (isInstance<Int>("ok")) return "NOK is Int"
    if (classLiteralName<String>() != "String") return "NOK class literal"
    return "OK"
}
