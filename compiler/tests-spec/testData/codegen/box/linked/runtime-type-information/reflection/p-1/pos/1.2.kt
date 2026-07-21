// WITH_STDLIB
// WITH_REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, reflection -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: KClass.members provides declaration introspection at runtime
 */
// TESTCASE NUMBER: 1

fun box(): String {
    if (!String::class.members.any { it.name == "length" }) return "NOK"
    return "OK"
}
