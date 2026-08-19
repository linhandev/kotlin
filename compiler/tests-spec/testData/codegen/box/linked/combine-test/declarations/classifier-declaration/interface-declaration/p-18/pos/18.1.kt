// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 18 -> sentence 18
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 18 -> sentence 18
 *                declarations, declarations-with-type-parameters -> paragraph 18 -> sentence 18
 * NUMBER: 1
 * DESCRIPTION: generic interface default getter return type follows implementing class type argument
 */

// TESTCASE NUMBER: 1
interface Holder<T> {
    val value: T? get() = null
}

class StringHolder : Holder<String>

class IntHolder : Holder<Int>

fun box(): String {
    if (StringHolder().value != null) return "NOK: string-default-null"
    if (IntHolder().value != null) return "NOK: int-default-null"
    val asString: Holder<String> = StringHolder()
    if (asString.value != null) return "NOK: string-via-interface"
    val asInt: Holder<Int> = IntHolder()
    if (asInt.value != null) return "NOK: int-via-interface"
    return "OK"
}
