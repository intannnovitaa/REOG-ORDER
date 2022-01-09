import java.io.FileWriter
import java.io.IOException
import java.io.PrintWriter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.pow
import kotlin.math.sqrt

class tes {
    var data: ArrayList<ArrayList<Float>>
    var clusterCenters: ArrayList<ArrayList<Float>>
    private lateinit var u: Array<FloatArray>
    private lateinit var u_pre: Array<FloatArray>
    private var clusterCount = 0
    private var iteration = 0
    private var dimension = 0
    private val fuzziness: Int
    private val epsilon: Double
    var finalError = 0.0
    fun run(clusterNumber: Int, iter: Int, data: ArrayList<ArrayList<Float>>) {
        clusterCount = clusterNumber
        iteration = iter
        this.data = data

        //start algorithm
        //1 assign initial membership values
        assignInitialMembership()
        for (i in 0 until iteration) {
            //2 calculate cluster centers
            calculateClusterCenters()

            //3
            updateMembershipValues()

            //4
            finalError = checkConvergence()
            if (finalError <= epsilon) break
        }
    }

    /**
     * in this function we generate random data with specific option
     * @param numberOfData
     * @param dimension
     * @param minRange
     * @param maxRange
     */
    fun createRandomData(
        numberOfData: Int,
        dimension: Int,
        minRange: Int,
        maxRange: Int,
        clusterCount: Int
    ) {
        this.dimension = dimension
        val centroids: ArrayList<ArrayList<Int>> = ArrayList()
        centroids.add(ArrayList())
        val numberOfDataInEachArea = IntArray(clusterCount)
        val range = maxRange - minRange + 1
        val step = range / (clusterCount + 1)
        for (i in 1..clusterCount) {
            centroids[0].add(minRange + i * step)
        }
        for (i in 0 until dimension - 1) {
            centroids.add(centroids[0].clone() as ArrayList<Int>)
        }
        val variance = (centroids[0][1] - centroids[0][0]) / 2.5
        for (i in 0 until dimension) {
            Collections.shuffle(centroids[i])
        }
        val r = Random()
        var sum = 0
        for (i in 0 until clusterCount) {
            val rg: Int = r.nextInt(50) + 10
            numberOfDataInEachArea[i] = rg
            sum += rg
        }
        for (i in 0 until clusterCount) numberOfDataInEachArea[i] = (numberOfDataInEachArea[i]
            .toDouble() / sum * numberOfData).toInt()
        val fRandom = Random()
        for (i in 0 until clusterCount) {
            for (j in 0 until numberOfDataInEachArea[i]) {
                val tmp: ArrayList<Float> = ArrayList()
                for (k in 0 until dimension) {
                    tmp.add((centroids[k][i] + fRandom.nextGaussian() * variance) as Float)
                }
                data.add(tmp)
            }
        }
    }

    /**
     * this function generate membership value for each data
     */
    private fun assignInitialMembership() {
        u = Array(data.size) { FloatArray(clusterCount) }
        u_pre = Array(data.size) { FloatArray(clusterCount) }
        val r = Random()
        for (i in 0 until data.size) {
            var sum = 0f
            for (j in 0 until clusterCount) {
                u[i][j] = r.nextFloat() * 10 + 1
                sum += u[i][j]
            }
            for (j in 0 until clusterCount) {
                u[i][j] = u[i][j] / sum
            }
        }
    }

    /**
     * in this function we calculate value of each cluster
     */
    private fun calculateClusterCenters() {
        clusterCenters.clear()
        for (i in 0 until clusterCount) {
            val tmp: ArrayList<Float> = ArrayList()
            for (j in 0 until dimension) {
                var cluster_ij: Float
                var sum1 = 0f
                var sum2 = 0f
                for (k in 0 until data.size) {
                    val tt = u[k][i].toDouble().pow(fuzziness.toDouble())
                    sum1 += (tt * data[k][j]).toFloat()
                    sum2 += tt.toFloat()
                }
                cluster_ij = sum1 / sum2
                tmp.add(cluster_ij)
            }
            clusterCenters.add(tmp)
        }
    }

    /**
     * in this function we will update membership value
     */
    private fun updateMembershipValues() {
        for (i in 0 until data.size) {
            for (j in 0 until clusterCount) {
                u_pre[i][j] = u[i][j]
                var sum = 0f
                val upper = Distance(data[i], clusterCenters[j])
                for (k in 0 until clusterCount) {
                    val lower = Distance(data[i], clusterCenters[k])
                    sum += (upper / lower).toDouble().pow((2 / (fuzziness - 1)).toDouble())
                        .toFloat()
                }
                u[i][j] = 1 / sum
            }
        }
    }

    /**
     * get norm 2 of two point
     * @param p1
     * @param p2
     * @return
     */
    private fun Distance(p1: ArrayList<Float>, p2: ArrayList<Float>): Float {
        var sum = 0f
        for (i in 0 until p1.size) {
            sum += (p1[i] - p2[i]).toDouble().pow(2.0).toFloat()
        }
        sum = sqrt(sum.toDouble()).toFloat()
        return sum
    }

    /**
     * we calculate norm 2 of ||U - U_pre||
     * @return
     */
    private fun checkConvergence(): Double {
        var sum = 0.0
        for (i in 0 until data.size) {
            for (j in 0 until clusterCount) {
                sum += (u[i][j] - u_pre[i][j]).toDouble().pow(2.0)
            }
        }
        return Math.sqrt(sum)
    }

    /**
     * write random generated data to file for visualizing
     * @throws IOException
     */
    @Throws(IOException::class)
    fun writeDataToFile(inpData: ArrayList<ArrayList<Float>>, fileName: String) {
        val fileWriter = FileWriter("./$fileName.csv")
        val printWriter = PrintWriter(fileWriter)
        for (i in 0 until inpData.size) {
            var res = ""
            for (j in 0 until inpData[i].size) {
                if (j == inpData[i].size - 1) res += inpData[i][j] else res += inpData[i][j].toString() + ","
            }
            printWriter.println(res)
        }
        printWriter.close()
    }

    init {
        data = ArrayList()
        clusterCenters = ArrayList()
        fuzziness = 2
        epsilon = 0.01
    }
}