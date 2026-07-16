// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: built-in-types-and-their-semantics, kotlin.reflect.kclass -> paragraph 1 -> sentence 1
 * PRIMARY LINKS: call-and-property-access-class-literals -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: class literals have type kotlin.reflect.KClass for runtime-available types
 * HELPERS: checkType
 */

import kotlin.reflect.KClass

// TESTCASE NUMBER: 1
fun case_1() {
    val c1: KClass<String> = String::class
    val c2: KClass<Int> = Int::class
    checkSubtype<KClass<String>>(c1)
    checkSubtype<KClass<String>>(String::class)
    (c1 == String::class) checkType { check<Boolean>() }
}


// TESTCASE NUMBER: 2
fun case_2(s: String) {
    val c: KClass<out String> = s::class
    checkSubtype<KClass<out String>>(c)
    c.simpleName checkType { check<String?>() }
}
