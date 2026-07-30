// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 255 -> sentence 255
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 255 -> sentence 255
 *                type-system, introduction-1 -> paragraph 255 -> sentence 255
 *                inheritance, inheriting -> paragraph 255 -> sentence 255
 * NUMBER: 1
 * DESCRIPTION: a class may implement a generic interface with a nullable type argument; contrasts with p-21/p-147 class Box<String?>, p-231 non-nullable interface producers, and p-246 nullable Box used mainly for default isNull
 */

// TESTCASE NUMBER: 1
interface Holder<T> {
    val v: T
}

class NullStringHolder : Holder<String?> {
    override val v: String? = null
}

class PresentStringHolder : Holder<String?> {
    override val v: String? = "ok"
}

// TESTCASE NUMBER: 2
interface Box<T> {
    fun get(): T
}

class NullIntBox : Box<Int?> {
    override fun get(): Int? = null
}

class PresentIntBox : Box<Int?> {
    override fun get(): Int? = 7
}

// TESTCASE NUMBER: 3
interface Factory<T> {
    fun create(): T
}

class NullBoolFactory : Factory<Boolean?> {
    override fun create(): Boolean? = null
}

class PresentBoolFactory : Factory<Boolean?> {
    override fun create(): Boolean? = true
}

fun box(): String {
    if (NullStringHolder().v != null) return "NOK: null-string"
    if (PresentStringHolder().v != "ok") return "NOK: present-string"
    val asHolder: Holder<String?> = NullStringHolder()
    if (asHolder.v != null) return "NOK: via-holder"

    if (NullIntBox().get() != null) return "NOK: null-int"
    if (PresentIntBox().get() != 7) return "NOK: present-int"
    val asBox: Box<Int?> = PresentIntBox()
    if (asBox.get() != 7) return "NOK: via-box"

    if (NullBoolFactory().create() != null) return "NOK: null-bool"
    if (PresentBoolFactory().create() != true) return "NOK: present-bool"
    val asFactory: Factory<Boolean?> = NullBoolFactory()
    if (asFactory.create() != null) return "NOK: via-factory"
    return "OK"
}
