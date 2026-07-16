// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION -EXTENSION_SHADOWED_BY_MEMBER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: overload-resolution, bidirectional-resolution-for-callable-calls -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: bidirectional resolution links overloaded pick1162 with ::foo1162 using second argument type
 */

interface Common1162
class Type1162A : Common1162
class Type1162B : Common1162

class Host1162 {
    fun foo1162(x: Type1162A): Type1162A = x
}

fun Host1162.foo1162(i: Type1162B): Type1162B = i

fun <T : Common1162> pick1162(call: (T) -> T, x: T): T = call(x)

// TESTCASE NUMBER: 1
fun case_1(host: Host1162) {
    pick1162(host::foo1162, Type1162A())
    pick1162(host::foo1162, Type1162B())
}
