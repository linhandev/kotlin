// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 266 -> sentence 266
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 266 -> sentence 266
 *                declarations, property-declaration -> paragraph 266 -> sentence 266
 * NUMBER: 1
 * DESCRIPTION: a class may read its own private members from instance methods; contrasts with p-265 outside-class failure and with p-69 single constructor-property getter
 */

// TESTCASE NUMBER: 1
class Vault(private val secret: Int) {
    fun get(): Int = secret
}

// TESTCASE NUMBER: 2
class Lock {
    private fun code(): Int = 2
    fun open(): Int = code()
}

// TESTCASE NUMBER: 3
class Capsule {
    private val hidden: String = "x"
    fun reveal(): String = hidden
    fun length(): Int = hidden.length
}

fun box(): String {
    if (Vault(1).get() != 1) return "NOK: vault-1"
    if (Vault(7).get() != 7) return "NOK: vault-7"
    val v = Vault(3)
    if (v.get() != 3) return "NOK: vault-via"

    if (Lock().open() != 2) return "NOK: lock"
    val lock = Lock()
    if (lock.open() != 2) return "NOK: lock-via"

    if (Capsule().reveal() != "x") return "NOK: capsule-reveal"
    if (Capsule().length() != 1) return "NOK: capsule-length"
    val c = Capsule()
    if (c.reveal() != "x" || c.length() != 1) return "NOK: capsule-via"
    return "OK"
}
