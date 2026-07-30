// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, expressions, call-and-property-access-expressions, callable-references -> paragraph 8 -> sentence 8
 * PRIMARY LINKS: type-system, type-kinds, function-types -> paragraph 8 -> sentence 8
 *                expressions, type-checking-and-containment-checking-expressions, type-checking-expressions -> paragraph 8 -> sentence 8
 * NUMBER: 1
 * DESCRIPTION: class literal User::class returns KClass<User> not a function type, verifying runtime semantics and distinction from callable references
 */

data class User(val name: String)

// TESTCASE NUMBER: 1
fun test(): Any = User::class

fun box(): String {
    val k = test()
    if (k !is kotlin.reflect.KClass<*>) return "NOK: not KClass"
    if (k != User::class) return "NOK: identity"
    if (!User::class.isInstance(User("a"))) return "NOK: isInstance"

    // Callable reference remains a function type, distinct from the class literal
    val ctor: (String) -> User = ::User
    if (ctor("a").name != "a") return "NOK: ctor-ref"
    if ((ctor as Any) is kotlin.reflect.KClass<*>) return "NOK: ctor must not be KClass"
    return "OK"
}
