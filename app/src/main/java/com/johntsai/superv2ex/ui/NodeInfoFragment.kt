package com.johntsai.superv2ex.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.johntsai.superv2ex.NodeDetailActivity
import com.johntsai.superv2ex.R
import com.johntsai.superv2ex.adapter.NodeInfoRecyclerViewAdapter
import com.johntsai.superv2ex.adapter.OnItemClickListener
import com.johntsai.superv2ex.data.NodeData
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException

class NodeInfoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NodeInfoRecyclerViewAdapter
    private lateinit var observable: Observable<Response>
    private val client: OkHttpClient = OkHttpClient()
    private val dataList: MutableList<NodeData> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_node_info, container, false)
        recyclerView = view.findViewById(R.id.recycler_view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = NodeInfoRecyclerViewAdapter(view.context, dataList)
        val intent = Intent(view.context, NodeDetailActivity::class.java)
        adapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(view: View, position: Int) {
                val data = dataList[position]
                intent.putExtra("href", data.href)
                startActivity(intent)
            }
        })
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter


        observable = Observable.unsafeCreate { t ->
            try {
                val res = client.newCall(Request.Builder()
                        .url("https://www.v2ex.com/planes")
                        .build())
                        .execute()
                t.onNext(res)
                t.onComplete()
                if (!res.isSuccessful) {
                    t.onError(Error("response error"))
                }
            } catch (e: IOException) {
                t.onError(e)
            }
        }

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Response> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: Response) {
                        val string = t.body()?.string()
                        val document: Document = Jsoup.parse(string)


                        val allElements = document.body().allElements
                        allElements?.filter { e -> e.className().equals("item_node") }?.map { e ->
                            dataList.add(NodeData(e.text(), e.attr("href")))
                        }
                        adapter.notifyDataSetChanged()
                    }

                    override fun onError(e: Throwable) {
                    }

                })

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }


}