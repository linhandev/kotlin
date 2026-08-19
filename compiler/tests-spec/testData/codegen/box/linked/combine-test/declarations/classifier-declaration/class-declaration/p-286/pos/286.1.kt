// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 286 -> sentence 286
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 286 -> sentence 286
 *                declarations, classifier-declaration, classifier-declaration-scopes -> paragraph 286 -> sentence 286
 * NUMBER: 1
 * DESCRIPTION: private companion object members are visible to instance members of the declaring class; covers function, property, and named companion; contrasts with next-point outside-class failure
 */

// TESTCASE NUMBER: 1
class Host {
    private companion object {
        fun secret(): Int = 1
    }

    fun ok(): Int = secret()
}

// TESTCASE NUMBER: 2
class Vault {
    private companion object {
        val key: Int = 7
    }

    fun unlock(): Int = key
}

// TESTCASE NUMBER: 3
class Token {
    private companion object Store {
        fun tag(): String = "T"
    }

    fun label(): String = tag()
}

fun box(): String {
    if (Host().ok() != 1) return "NOK: host"
    val h = Host()
    if (h.ok() != 1) return "NOK: host-via"

    if (Vault().unlock() != 7) return "NOK: vault"
    val v = Vault()
    if (v.unlock() != 7) return "NOK: vault-via"

    if (Token().label() != "T") return "NOK: token"
    val t = Token()
    if (t.label() != "T") return "NOK: token-via"
    return "OK"
}
