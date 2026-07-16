// WITH_STDLIB
// WITH_REFLECT

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: runtime-type-information, reflection -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: kotlin.reflect.KClass exposes simpleName, qualifiedName, isInstance, and supertypes at runtime
 */
// TESTCASE NUMBER: 1

fun box(): String {
    val k = String::class
    if (k.simpleName != "String") return "NOK simpleName"
    if (k.qualifiedName != "kotlin.String") return "NOK qualifiedName"
    if (!k.isInstance("ok")) return "NOK isInstance positive"
    if (k.isInstance(42)) return "NOK isInstance negative"
    if (k.supertypes.none { it.classifier == Comparable::class }) return "NOK supertypes"
    return "OK"
}
