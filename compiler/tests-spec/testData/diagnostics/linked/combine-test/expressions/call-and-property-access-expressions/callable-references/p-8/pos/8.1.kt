// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 8 -> sentence 8
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: class literal User::class infers KClass<User>, distinct from callable references of function type, verifying type inference
 * HELPERS: checkType
 */

data class User(val name: String)

// TESTCASE NUMBER: 1
fun case1() {
    val k: kotlin.reflect.KClass<User> = User::class
    checkSubtype<kotlin.reflect.KClass<User>>(k)
    val ctor: (String) -> User = ::User
    checkSubtype<(String) -> User>(ctor)
}
