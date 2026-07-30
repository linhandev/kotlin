// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -DEBUG_INFO_SMARTCAST
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, class-literals -> paragraph 20 -> sentence 20
 * PRIMARY LINKS: type-system, introduction-1 -> paragraph 20 -> sentence 20
 * NUMBER: 1
 * DESCRIPTION: after nullable smart cast, value::class infers KClass<out String>; Number receiver ::class is KClass<out Number>
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
fun case1(nullable: String?) {
    if (nullable != null) {
        checkSubtype<kotlin.reflect.KClass<out String>>(nullable::class)
        checkSubtype<kotlin.reflect.KClass<String>>(String::class)
    }
}

// TESTCASE NUMBER: 2
fun case2(n: Number) {
    checkSubtype<kotlin.reflect.KClass<out Number>>(n::class)
    checkSubtype<kotlin.reflect.KClass<Int>>(Int::class)
    checkSubtype<kotlin.reflect.KClass<Number>>(Number::class)
}
