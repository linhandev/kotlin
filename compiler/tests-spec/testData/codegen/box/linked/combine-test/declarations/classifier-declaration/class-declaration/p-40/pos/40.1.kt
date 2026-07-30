// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 40 -> sentence 40
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 40 -> sentence 40
 * NUMBER: 1
 * DESCRIPTION: F-bound style T Builder T constraint
 */

// TESTCASE NUMBER: 1
abstract class Builder<T : Builder<T>> { abstract fun self(): T }

class UserBuilder : Builder<UserBuilder>() { override fun self() = this }

fun test(): UserBuilder = UserBuilder().self()

fun box(): String {
    if (test() !== UserBuilder() && test()::class != UserBuilder::class) return "NOK"
    return "OK"
}
